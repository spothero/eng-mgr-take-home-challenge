from .db import session_scope
from .models import User


def create_user():
    return User(
        first_name="John",
        last_name="Smith",
        email="jsmith@example.com",
    )


def run():
    with session_scope() as session:
        u = create_user()
        session.add(u)
        session.commit()
