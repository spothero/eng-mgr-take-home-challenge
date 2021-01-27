from .db import session_scope
from .models import User


def create_user() -> User:
    return User(
        first_name="John",
        last_name="Smith",
        email="jsmith@example.com",
    )


def run() -> None:
    with session_scope() as session:
        u = create_user()
        session.add(u)
        session.commit()
