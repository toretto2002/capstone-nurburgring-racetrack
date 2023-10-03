import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import OpenAI from 'openai';
import { IChatCompletion } from 'src/app/interfaces/ichat-completion';

@Injectable({
  providedIn: 'root',
})
export class FaqsService {
  private apiKey: string =
    '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';

  constructor(private http: HttpClient) {}

  async getResponse(userInput: string) {
    let reqHeaders: HttpHeaders = new HttpHeaders();
    const openai = new OpenAI({
      apiKey: this.apiKey,
      dangerouslyAllowBrowser: true,
    });

    try {
      const completion = await openai.chat.completions.create({
        messages: [{ role: 'system', content: userInput }],
        model: 'gpt-3.5-turbo',
      });
    } catch (error) {
      console.error(error);
      throw error;
    }
  }
}
