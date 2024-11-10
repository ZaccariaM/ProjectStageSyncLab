import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { DividerModule } from 'primeng/divider';
import { InputTextModule } from 'primeng/inputtext';
import { CheckboxModule } from 'primeng/checkbox';
import { StyleClassModule } from 'primeng/styleclass';

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [
        FormsModule,
        ReactiveFormsModule,
        ButtonModule,
        RippleModule,
        DividerModule,
        InputTextModule,
        CheckboxModule,
        StyleClassModule
    ],
    templateUrl: './login.component.html',
    styleUrl: './login.component.css'
})
export class LoginComponent {
    constructor(private router: Router){ }
    signUp(): void{
        this.router.navigate(['/new']);
    }
    chatRedirect(): void{
        this.router.navigate(['/chat']);
    }
}
