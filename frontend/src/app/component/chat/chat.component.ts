import { AfterViewChecked, Component, ElementRef, OnInit, signal, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { BackendService } from '../../service/backend.service';

import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { FieldsetModule } from 'primeng/fieldset';
import { MenubarModule } from 'primeng/menubar';
import { BlockUIModule } from 'primeng/blockui';

interface ChatMessage {
    message: string;
    id: string;
}

@Component({
    selector: 'app-chat',
    standalone: true,
    imports: [
        FormsModule,
        CommonModule,
        ButtonModule,
        InputTextModule,
        FieldsetModule,
        MenubarModule,
        BlockUIModule
    ],
    templateUrl: './chat.component.html',
    styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit, AfterViewChecked {
    inputText!: string;
    icon_val: string = 'pi pi-send';
    clicked: boolean = false;
    chat = signal<ChatMessage[]>([]);
    @ViewChild('show') show!: ElementRef;

    noLogin: boolean = false;

    constructor(private router: Router, private backend: BackendService) { }

    ngOnInit(): void {
        this.history();
    }

    ngAfterViewChecked(): void {
        this.scrollToLast();
    }

    //fecth history from backend
    history(): void {
        this.backend.history().subscribe({
            next: (arg) => {
                console.log(arg);   //for debugging
                const a = arg.map((msg: { message: string; sender: boolean; }) => ({ message: msg.message, id: msg.sender ? 'user' : 'ai' }));
                this.chat.set(a);
            },
            error: (error) => {
                console.log('Error in fetching history');   //for debugging
                this.noLogin = true;    //not really useful
                this.router.navigate(['/login']);
            }
        }
        );
    }

    //send message to backend and wait for response
    send(): void {
        this.loadingButton();

        const prompt = this.inputText;
        this.clear();

        this.chat.update((message) => [...message, { message: prompt, id: 'user' }]);
        this.backend.chat(prompt).subscribe(arg => {
            console.log(arg);   //for debugging
            this.chat.update((message) => [...message, { message: arg, id: 'ai' }]);
            this.loadingButton();
        });
    }

    //logout and redirect to login
    logout(): void {
        this.backend.logout();
        this.router.navigate(['/login']);
    }

    //update icon of the send button while waiting for response
    private loadingButton(): void {
        this.clicked = !this.clicked;
        if (this.clicked) {
            this.icon_val = 'pi pi-spin pi-spinner';
        } else {
            this.icon_val = 'pi pi-send';
        }

    }

    //clear input text
    private clear(): void {
        this.inputText = '';
    }

    //scroll to last message in the chat
    private scrollToLast(): void {
        if (this.show) {
            this.show.nativeElement.scrollIntoView({ behavior: 'smooth' });
        }
    }
}
