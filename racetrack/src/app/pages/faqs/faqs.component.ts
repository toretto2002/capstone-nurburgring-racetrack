import { Component } from '@angular/core';
import { FaqsService } from './faqs.service';
import { IChatCompletion } from 'src/app/interfaces/ichat-completion';
import OpenAI from 'openai';

@Component({
  selector: 'app-faqs',
  templateUrl: './faqs.component.html',
  styleUrls: ['./faqs.component.scss'],
})
export class FaqsComponent {
  userInput: string = '';
  chatMessages: string[] = [];

  private apiKey: string =
    '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';

  async sendQuestion() {
    if (this.userInput.trim() !== '') {
      this.chatMessages.push(this.userInput);
      console.log(this.chatMessages);
      this.userInput = '';
    }

    const openai = new OpenAI({
      apiKey: this.apiKey,
      dangerouslyAllowBrowser: true,
    });

    try {
      const completion = await openai.chat.completions
        .create({
          messages: [
            { role: 'system', content: 'You are a helpful assistant.' },
            { role: 'user', content: this.userInput },
          ],
          model: 'gpt-3.5-turbo',
        })
        .then((response) => {
          console.log(response.choices[0].message.content);

          let message = response.choices[0].message.content;

          if (message != null) {
            this.chatMessages.push(message);
          }
        });
    } catch (error) {
      console.error(error);
      throw error;
    }
  }
}
