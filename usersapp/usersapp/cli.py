from .db import session_scope
from .models import User


Result = int


def register_user(user: User) -> Result:
    pass


def send_welcome_message(email: str) -> None:
    with session_scope() as session:
        u = session.query(User).filter(User.email == email).first()
        print(f"user is {u.__dict__}")


def run(args=None):
    """
    Runs the app from the command line
    """
    send_welcome_message("jsmith@example.com")
