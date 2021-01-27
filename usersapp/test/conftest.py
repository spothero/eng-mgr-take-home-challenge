from usersapp.db import get_engine
from sqlalchemy.orm import sessionmaker
import pytest

engine = get_engine()
Session = sessionmaker()


@pytest.fixture(scope="module")
def connection():
    connection = engine.connect()
    yield connection
    connection.close()


@pytest.fixture(scope="function")
def session(connection):
    """Fixture to execute asserts before and after a test is run"""
    transaction = connection.begin()
    session = Session(bind=connection)

    yield session

    session.close()
    transaction.rollback()
