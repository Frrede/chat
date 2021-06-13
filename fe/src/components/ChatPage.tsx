import React from 'react';
import {Button, Card, CardActions, CardContent, Container, makeStyles, TextField, Typography} from '@material-ui/core';
import {ChatList} from './ChatList';

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

  const messages = [
    {
      id: 'hfk',
      token: '123',
      name: 'Test 1',
      message: 'as',
      date: new Date()
    },
    {
      id: 'jlj',
      token: '124',
      name: 'asasf',
      message: 'as',
      date: new Date()
    }
  ];

  return (
    <Container maxWidth="sm" className={classes.container}>
      <Typography variant="h4" component="h1">
        Chat
      </Typography>
      <TextField variant="outlined" label="Name" />
      <Card variant="outlined" className={classes.content}>
        <CardContent className={classes.chatList}>
          <ChatList messages={messages} token="124"/>
        </CardContent>
        <CardActions className={classes.footer}>
          <TextField variant="outlined" className={classes.text}/>
          <Button size="small">Send</Button>
        </CardActions>
      </Card>
    </Container>
  );
}
