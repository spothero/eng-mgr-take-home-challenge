from sqlalchemy.orm.session import Session
from .factories import user_factory
from usersapp.cli import _send_welcome_message


def test_send_welcome_email(session: Session) -> None:
    u = user_factory()
    session.add(u)

    # Call function under test
    _send_welcome_message(session, u)

    assert len(u.notifications) == 1
    notification = u.notifications[0]
    assert notification.status_code == 201
