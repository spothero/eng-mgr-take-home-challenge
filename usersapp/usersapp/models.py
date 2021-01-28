from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import (
    Column,
    DateTime,
    ForeignKey,
    Integer,
    SmallInteger,
    String,
    Text,
    TIMESTAMP,
    text,
)
from sqlalchemy.orm import relationship, validates
import datetime
import re


Base = declarative_base()
metadata = Base.metadata


class User(Base):

    __tablename__ = "users"

    id = Column(Integer, primary_key=True)
    first_name = Column(String(255), nullable=False)
    last_name = Column(String(255), nullable=False)
    email = Column(String(255), nullable=False, unique=True)
    created_at = Column(
        TIMESTAMP,
        nullable=False,
        server_default=text("CURRENT_TIMESTAMP"),
    )
    updated_at = Column(DateTime, onupdate=datetime.datetime.now)

    notifications = relationship("Notification")

    @validates("email")
    def validate_email(self, key, email):
        """
        Validates the :attr:`email` format.
        :raises: :exc:`~exceptions.ValueError` when it's invalid
        """
        if email is None:
            return
        email = email.strip()
        if re.match("[^@]+@[^@]+\.[^@]+", email):
            return email
        raise ValueError("{0!r} is an invalid email address".format(email))


class Notification(Base):

    __tablename__ = "notifications"

    id = Column(Integer, primary_key=True)
    user_id = Column(
        Integer, ForeignKey("users.id", ondelete="cascade"), nullable=False
    )
    body = Column(Text(), nullable=False)
    status_code = Column(SmallInteger, nullable=False, server_default="0")
    created_at = Column(
        TIMESTAMP,
        nullable=False,
        server_default=text("CURRENT_TIMESTAMP"),
    )

    user = relationship("User", back_populates="notifications")
