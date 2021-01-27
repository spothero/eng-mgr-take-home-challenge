from usersapp.models import User


def user_factory() -> User:
    return User(first_name="John", last_name="Smith", email="johns@example.com")
