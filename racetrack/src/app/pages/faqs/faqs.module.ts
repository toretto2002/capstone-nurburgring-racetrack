import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FaqsRoutingModule } from './faqs-routing.module';
import { FaqsComponent } from './faqs.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [FaqsComponent],
  imports: [CommonModule, FaqsRoutingModule, FormsModule],
})
export class FaqsModule {}
