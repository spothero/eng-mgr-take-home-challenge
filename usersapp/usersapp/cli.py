from .db import session_scope
from .models import User, Notification
from sqlalchemy.orm.session import Session


Result = int


class EmailAPIProxy:
    def send(self, notification: Notification) -> None:
        raise NotImplementedError


def register_user(user: User) -> Result:
    pass


def send_welcome_message(db: Session, email: str) -> None:
    user = db.query(User).filter(User.email == email).first()

    if not user:
        raise Exception("Sorry, no user was found")

    # Create welcome email
    message = "Welcome to our app!"

    notification = Notification(
        user=user,
        body=message,
    )
    db.add(notification)

    print(f"user is {user.__dict__}")


def run(args=None):
    """
    Runs the app from the command line
    """
    with session_scope() as session:
        send_welcome_message(session, "jsmith@example.com")
