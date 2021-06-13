import React from 'react';
import {act, render, screen} from '@testing-library/react';
import axios from 'axios';
import {ChatPage} from './ChatPage';

jest.mock('axios');
const mockedAxios = axios as jest.Mocked<typeof axios>;

describe('ChatPage', () => {

  beforeEach(async () => {
    mockedAxios.get.mockImplementation(() => {
      return Promise.resolve({
        data: {
          messages: [
            {
              id: '1',
              token: 'TOKEN',
              name: 'Testy McTest',
              message: 'Just a test',
              date: '2021-06-13'
            }
          ]
        }
      });
    });
    await act(async () => {
      await render(<ChatPage />);
    });
  });

  it('should render the entry', () => {
    expect(screen.getByTestId('chat-list')).toHaveTextContent('Testy McTest');
    expect(screen.getByTestId('chat-list')).toHaveTextContent('Just a test');
    expect(screen.getByTestId('chat-list')).toHaveTextContent('2021-06-13');
  });

  it('should call backend', () => {
    expect(axios.get).toHaveBeenCalledWith('http://localhost:8080/chat', {
      params: undefined
    });
  });

});
