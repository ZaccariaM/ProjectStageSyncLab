import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

import { BackendService } from '../../service/backend.service';

import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { DividerModule } from 'primeng/divider';
import { InputTextModule } from 'primeng/inputtext';
import { CheckboxModule } from 'primeng/checkbox';
import { StyleClassModule } from 'primeng/styleclass';
import { PanelModule } from 'primeng/panel';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { BlockUIModule } from 'primeng/blockui';
import { ProgressSpinnerModule } from 'primeng/progressspinner';

@Component({
    selector: 'app-registration',
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
    templateUrl: './registration.component.html',
    styleUrl: './registration.component.css'
})
export class RegistrationComponent {
    registrationForm: FormGroup
    registrationResolve: boolean = false;

    constructor(private router: Router, private backend: BackendService, private messageService: MessageService) {
        this.registrationForm = new FormGroup({
            username: new FormControl('', [Validators.required, Validators.minLength(3)]),
            email: new FormControl('', [Validators.required, Validators.email]),
            password: new FormControl('', [Validators.required, Validators.minLength(6)]),
            confirmPassword: new FormControl('', [Validators.required]),
        });
    }

    //send registration request to backend
    ngOnSubmit(): void {
        this.loading();

        if (this.registrationForm.valid && this.passwordMatchValidator()) {
            this.backend.register(this.registrationForm.value.username, this.registrationForm.value.email, this.registrationForm.value.password).subscribe({
                next: (response) => {
                    console.log(response);  //for debugging
                    //login and redirect to chat
                    this.loading();
                    this.showError('success', 'Registration successful', 'You can now login with your new account!');
                },
                error: (error) => {
                    console.log(error);     //for debugging
                    this.loading();
                    this.showError('error', 'Registration failed', 'Email or username already exists!')
                }
            });
        }
    }

    //redirect to login page
    signIn(): void {
        this.router.navigate(['/login']);
    }

    //check for matching passwords
    passwordMatchValidator() {
        const password = this.registrationForm.get('password')?.value;
        const confirmPassword = this.registrationForm.get('confirmPassword')?.value;

        if (password !== confirmPassword) {
            console.log('Passwords do not match!');     //for debugging
            this.loading();
            this.showError('error', 'Mismatched password', 'Passwords do not match!');
            return false;
        } else {
            return true;
        }
    }

    //block module and spinner
    private loading(): void {
        this.registrationResolve = !this.registrationResolve;
    }

    //toast message
    private showError(severity: string, summary: string, detail: string): void {
        this.messageService.add({ severity: severity, summary: summary, detail: detail });
    }
}
