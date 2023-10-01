import { Component } from '@angular/core';
import { FaqsService } from './faqs.service';

@Component({
  selector: 'app-faqs',
  templateUrl: './faqs.component.html',
  styleUrls: ['./faqs.component.scss'],
})
export class FaqsComponent {
  userInput: string = '';
  chatMessages: string[] = [];

  constructor(private faqsSvc: FaqsService) {}

  sendQuestion() {
    if (this.userInput.trim() !== '') {
      this.chatMessages.push(this.userInput);
      console.log(this.chatMessages);
      this.userInput = '';
    }

    this.faqsSvc.getResponse(this.userInput).subscribe((response) => {
      const aiResponse = response;
      console.log(aiResponse);
      this.chatMessages.push(`Assistente: ${aiResponse}`);
      this.userInput = '';
    });
  }
}
