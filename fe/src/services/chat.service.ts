import axios from 'axios';
import {MessageRequest} from '../models/MessageRequest';
import {MessageResponse} from '../models/MessageResponse';

const BACKEND_URL = 'http://localhost:8080/chat';

export async function getMessages(startingDate?: String): Promise<MessageResponse> {
  let params;
  if (startingDate) {
    params = {
      startingDate
    };
  }
  const response = await axios.get(BACKEND_URL, {
    params
  });
  return response.data;
}

export async function sendMessage(message: MessageRequest): Promise<void> {
  const requestBody = {
    ...message,
    token: undefined
  }
  return axios.post(BACKEND_URL, requestBody, {
    headers: {
      token: message.token
    }
  });
}
