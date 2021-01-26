from .db import session_scope
from .models import User


def create_user():
    return User(
        first_name="John",
        last_name="Smith",
        email="jsmith@example.com",
    )


def add_data():
    u = create_user()


def run():
    add_data()
