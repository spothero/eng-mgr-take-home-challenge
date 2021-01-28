from .db import session_scope
from .models import User
from .services import register_user


def run(args=None):
    """
    Runs the app from the command line
    """
    with session_scope() as session:
        user = User(email="janesmith@", first_name="Jane", last_name="Smith")
        register_user(session, user)
