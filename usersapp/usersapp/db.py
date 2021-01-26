from typing import Any

from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from sqlalchemy.engine.base import Engine
from dotenv import load_dotenv
from pathlib import Path  # Python 3.6+ only
from contextlib import contextmanager
import os

env_path = Path(".") / ".env.dev"
load_dotenv(dotenv_path=env_path)


def get_engine() -> Engine:
    return create_engine(db_url(), echo=True)


def get_session() -> Any:
    Session = sessionmaker(bind=get_engine())
    return Session()


@contextmanager
def session_scope():
    """Provide a transactional scope around a series of operations."""
    session = get_session()
    try:
        yield session
        session.commit()
    except:
        session.rollback()
        raise
    finally:
        session.close()


def db_url() -> str:
    user = os.getenv("POSTGRES_USER")
    pwd = os.getenv("POSTGRES_PASSWORD")
    host = os.getenv("POSTGRES_HOST")
    port = os.getenv("POSTGRES_PORT")
    db = os.getenv("POSTGRES_DB")
    return f"postgresql://{user}:{pwd}@{host}:{port}/{db}"
