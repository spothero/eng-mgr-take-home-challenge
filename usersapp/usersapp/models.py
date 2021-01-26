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
from sqlalchemy.orm import relationship
import datetime


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
