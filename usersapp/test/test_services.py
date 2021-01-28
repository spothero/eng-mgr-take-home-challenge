from sqlalchemy.orm.session import Session
from unittest.mock import patch
from typing import List

from .factories import user_factory
from usersapp.services import EmailAPIProxy, register_user, _send_welcome_message
from usersapp.models import User, Notification


def assert_notifications(notifications: List[Notification], status_code: int) -> None:
    assert len(notifications) == 1
    notification = notifications[0]
    assert notification.status_code == status_code


def test_register_user__no_errors(session: Session) -> None:
    u = user_factory()

    register_user(session, u)

    located_user = session.query(User).filter(User.email == u.email).first()

    assert located_user
    assert located_user.first_name == u.first_name

    assert_notifications(u.notifications, 201)  # type: ignore


def test_register_user__api_fails(session: Session) -> None:
    u = user_factory()

    assert len(u.notifications) == 0  # type: ignore

    def my_side_effect(*args):
        raise Exception("Error calling the API")

    with patch.object(EmailAPIProxy, "send", side_effect=my_side_effect):
        # Call function under test
        register_user(session, u)

    # This is now correct...
    # We shouldn't have inconsistent state in our DB
    assert_notifications(u.notifications, 500)  # type: ignore
