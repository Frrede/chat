import React from 'react';
import {Typography} from '@material-ui/core';
import {Message} from '../models/Message';

export interface ChatEntryProps {
  message: Message;
}

export function ChatEntry(props: ChatEntryProps) {

  return (
    <>
      <Typography variant="caption" display="block" gutterBottom>
        { props.message.name }
      </Typography>
      <Typography variant="body1" gutterBottom>
        { props.message.message }
      </Typography>
      <Typography variant="caption" display="block" gutterBottom>
        { props.message.date }
      </Typography>
    </>
  );
}