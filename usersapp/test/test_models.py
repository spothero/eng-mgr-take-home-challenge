from sqlalchemy.orm.session import Session

from usersapp.models import User
from .factories import user_factory


def test_create_user(session: Session) -> None:
    u = user_factory()

    session.add(u)
    found_user = session.query(User).filter(User.email == u.email).one()
    print(found_user.__dict__)
    assert u == found_user
