from sqlalchemy.orm.session import Session
from .factories import user_factory
from usersapp.cli import send_welcome_message


def test_send_welcome_email(session: Session) -> None:
    u = user_factory()
    session.add(u)

    # Call function under test
    send_welcome_message(session, u.email)

    assert len(u.notifications) == 1
