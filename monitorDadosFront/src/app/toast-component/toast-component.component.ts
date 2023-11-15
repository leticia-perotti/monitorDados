import { Component, Input, inject } from '@angular/core';
import { MatSnackBarRef } from '@angular/material/snack-bar';

@Component({
  selector: 'app-toast-component',
  templateUrl: './toast-component.component.html',
  styleUrls: ['./toast-component.component.scss']
})
export class ToastComponentComponent {

  @Input() mensagem = ""

  snackBarRef = inject(MatSnackBarRef);
}
