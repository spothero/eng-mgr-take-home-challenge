from .models import User, Notification
from sqlalchemy.orm.session import Session
from typing import Optional


class EmailAPIProxy:
    """
    Simulates an email sending API
    """

    def send(self, notification: Notification) -> None:
        # Send the notification via an email provider API
        notification.status_code = 201


def register_user(db: Session, user: User) -> Optional[User]:
    db.add(user)

    _send_welcome_message(db, user)

    return user


def _send_welcome_message(db: Session, user: User) -> User:
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

    return user
