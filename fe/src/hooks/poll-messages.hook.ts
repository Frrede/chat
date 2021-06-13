import {useEffect, useRef, useState} from 'react';
import {Message} from '../models/Message';
import {getMessages} from '../services/chat.service';

const POLLING_TIME = 3000;

export function usePollMessagesHook() {

  const [ messages, setMessages ] = useState<Message[]>();
  const interval = useRef<number>();

  useEffect(() => {
    loadMessages();
    interval.current = setInterval(() => {
      loadMessages();
    }, POLLING_TIME) as unknown as number;

    return () => {
      clearInterval(interval.current);
    }
  }, []);

  async function loadMessages() {
    const response = await getMessages();
    setMessages(response.messages);
  }

  return {
    messages,
    triggerReload: loadMessages
  };
}