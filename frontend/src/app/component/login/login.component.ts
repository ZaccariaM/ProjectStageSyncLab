import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { BackendService } from '../../service/backend.service';

import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { DividerModule } from 'primeng/divider';
import { InputTextModule } from 'primeng/inputtext';
import { CheckboxModule } from 'primeng/checkbox';
import { StyleClassModule } from 'primeng/styleclass';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { BlockUIModule } from 'primeng/blockui';
import { PanelModule } from 'primeng/panel';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';

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
        StyleClassModule,
        ProgressSpinnerModule,
        BlockUIModule,
        PanelModule,
        ToastModule
    ],
    providers: [MessageService],
    templateUrl: './login.component.html',
    styleUrl: './login.component.css'
})
export class LoginComponent {
    loginForm: FormGroup
    loginResolve: boolean = false;

    constructor(private router: Router, private backend: BackendService, private messageService: MessageService) {
        this.loginForm = new FormGroup({
            username: new FormControl('', [
                Validators.required,
                Validators.minLength(3),
                Validators.maxLength(20),
                Validators.pattern('^[a-zA-Z0-9]*$')
            ]),
            password: new FormControl('', [
                Validators.required,
                Validators.minLength(8),
                Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$')
            ])
        });
    }

    //redirect to registration page
    signUp(): void {
        this.router.navigate(['/new']);
    }

    //send login request to backend
    ngOnSubmit(): void {
        this.loading();

        this.backend.login(this.loginForm.value.username, this.loginForm.value.password).subscribe({
            next: (response) => {
                console.log(response);  //for debugging
                this.router.navigate(['/chat']);
            },
            error: (error) => {
                console.log(error);     //for debugging
                this.loading();
                this.showError();
            }
        });
    }

    //block module and spinner
    private loading(): void {
        this.loginResolve = !this.loginResolve;
    }

    //toast message
    private showError(): void {
        this.messageService.add({ severity: 'error', summary: 'Invalid Login', detail: 'Username or password is incorrect' });
    }
}
