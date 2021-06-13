import React from 'react';
import {ChatEntry} from './ChatEntry';
import {Message} from '../models/Message';
import {makeStyles} from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
  entry: {
    marginBottom: theme.spacing(4)
  },
  isUser: {
    background: theme.palette.grey[100]
  }
}));

export interface ChatListProps {
  token: String,
  messages: Message[];
}

export function ChatList(props: ChatListProps) {
  const classes = useStyles();

  function getClassName(message: Message): string {
    if (message.token === props.token) {
      return `${classes.entry} ${classes.isUser}`;
    }
    return classes.entry;
  }

  return (
    <>
      {
        props.messages.map(message => {
          return (
            <div className={getClassName(message)} key={'chat-' + message.id}>
              <ChatEntry
                message={message}
              />
            </div>

          );
        })
      }
    </>
  );
}