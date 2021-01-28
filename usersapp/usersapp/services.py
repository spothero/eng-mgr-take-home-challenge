from sqlalchemy.orm.session import Session
from pyservice import Context, action
from typing import Optional

from .models import User, Notification


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

    ctx = Context.make({"notification": notification})
    send_email(ctx)

    db.add(notification)

    return user


@action()
def send_email(ctx: Context) -> Context:
    notification = ctx["notification"]

    emailer = EmailAPIProxy()

    try:
        emailer.send(notification)
    except:
        # Log error
        notification.status_code = 500
        ctx.fail("Error occurred sending the API")

    return ctx
