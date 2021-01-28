from sqlalchemy.orm.session import Session
from unittest.mock import patch
from typing import cast
from pytest import raises

from .factories import user_factory
from usersapp.services import register_user, _send_welcome_message
from usersapp.models import User


def test_register_user__no_errors(session: Session) -> None:
    u = user_factory()

    with patch("usersapp.services._send_welcome_message") as send_welcome_message_mock:
        send_welcome_message_mock.return_value = u

        register_user(session, u)

        located_user = session.query(User).filter(User.email == u.email).first()

        assert located_user
        assert located_user.first_name == u.first_name


def test_send_welcome_email(session: Session) -> None:
    u = user_factory()
    session.add(u)

    # Call function under test
    _send_welcome_message(session, u)

    notifications = list(u.notifications)  # type: ignore
    assert len(notifications) == 1
    notification = notifications[0]
    assert notification.status_code == 201
