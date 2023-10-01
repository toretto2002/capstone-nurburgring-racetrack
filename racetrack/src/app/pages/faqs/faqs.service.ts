import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class FaqsService {
  private apiKey: string =
    'Bearer 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';

  constructor(private http: HttpClient) {}

  getResponse(userInput: string) {
    let reqHeaders: HttpHeaders = new HttpHeaders();

    reqHeaders = reqHeaders.set('Content-Type', 'application/json');
    reqHeaders = reqHeaders.set('Authorization', this.apiKey);

    console.log(reqHeaders);

    const requestBody = {
      prompt: userInput,
      max_tokens: 50,
    };

    return this.http.post(
      'https://api.openai.com/v1/engines/davinci/completions',
      requestBody,
      { headers: reqHeaders }
    );
  }
}
