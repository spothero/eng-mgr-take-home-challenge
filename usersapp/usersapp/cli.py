from .db import session_scope
from .models import User, Notification
from sqlalchemy.orm.session import Session


Result = int


class EmailAPIProxy:
    """
    Simulates an email sending API
    """

    def send(self, notification: Notification) -> None:
        # Send the notification via an email provider API
        notification.status_code = 201


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

    # Send the notification
    emailer = EmailAPIProxy()
    emailer.send(notification)

    db.add(notification)


def run(args=None):
    """
    Runs the app from the command line
    """
    with session_scope() as session:
        send_welcome_message(session, "jsmith@example.com")
