import React, {useEffect, useState} from 'react';
import {Button, Card, CardActions, CardContent, Container, makeStyles, TextField, Typography} from '@material-ui/core';
import {ChatList} from './ChatList';
import {sendMessage} from '../services/chat.service';
import {usePollMessagesHook} from '../hooks/poll-messages.hook';

const useStyles = makeStyles((theme) => ({
  container: {
    display: 'flex',
    flexDirection: 'column',
    height: '100vh'
  },
  content: {
    flex: 1,
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'space-between',
    marginTop: theme.spacing(2)
  },
  chatList: {
    overflow: 'auto'
  },
  footer: {
    display: 'flex',
    background: theme.palette.grey[200]
  },
  text: {
    flex: 1,
    background: theme.palette.background.default
  }
}));

export function ChatPage() {
  const classes = useStyles();

  const [ name, setName ] = useState<string>();
  const [ message, setMessage ] = useState<string>();
  const { messages, triggerReload } = usePollMessagesHook();
  const token = 'token' + name;

  async function sendNewMessage() {
    if (!name || !message) {
      return;
    }
    await sendMessage({
      token,
      name,
      message
    })
    triggerReload();
  }

  return (
    <Container maxWidth="sm" className={classes.container}>
      <Typography variant="h4" component="h1">
        Chat
      </Typography>
      <TextField variant="outlined" label="Name" onChange={(event) => setName(event.target.value)}/>
      <Card variant="outlined" className={classes.content}>
        <CardContent className={classes.chatList} data-testid="chat-list">
          <ChatList messages={messages} token={token} />
        </CardContent>
        <CardActions className={classes.footer}>
          <TextField variant="outlined" className={classes.text} onChange={(event) => setMessage(event.target.value)}/>
          <Button size="small" disabled={!name || !message} onClick={sendNewMessage}>Send</Button>
        </CardActions>
      </Card>
    </Container>
  );
}
