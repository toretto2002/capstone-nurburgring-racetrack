import { IChatChoice } from './ichat-choice';
import { IChatUsage } from './ichat-usage';

export interface IChatCompletion {
  id: string;
  object: string;
  created: number;
  model: string;
  choices: IChatChoice[];
  usage: IChatUsage;
}
