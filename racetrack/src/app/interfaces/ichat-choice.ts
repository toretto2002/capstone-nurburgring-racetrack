import { IChatMessage } from './ichat-message';

export interface IChatChoice {
  index: number;
  message: IChatMessage;
  finish_reason: string;
}
