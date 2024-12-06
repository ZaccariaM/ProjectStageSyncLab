import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import * as jwt_decode from 'jsonwebtoken';
import { response } from 'express';

@Injectable({
    providedIn: 'root'
})
export class BackendService {
    private apiUrl = "http://localhost:8080";

    constructor(private http: HttpClient) { }

    //get token
    getToken() {
        console.log('Bearer ' + localStorage.getItem('token')); //for debugging
        return 'Bearer ' + localStorage.getItem('token');
    }

    //login account
    login(username: string, password: string): Observable<any> {
        return this.http/*
            //if json is being sent
            .post<any>(`${this.apiUrl}/login`, { username, password })
            .pipe(
                tap((response) => {
                    if (response && response.token) {
                        localStorage.setItem('token', response.token);
                    }
                })
            );*/
            .post(`${this.apiUrl}/login`, { username, password }, { responseType: 'text' })
            .pipe(
                tap((response: string) => {
                    if (response) {
                        localStorage.setItem('token', response);
                    }
                })
            );
    }

    //logout account
    logout() {
        localStorage.removeItem('token');
    }

    //register account
    register(username: string, email: string, password: string): Observable<any> {
        return this.http.post<any>(`${this.apiUrl}/register`,
            { username, email, password },
            { responseType: 'text' as 'json' }
        );
    }

    //history account
    history(): Observable<any> {
        console.log(this.getToken());   //for debugging
        return this.http.get<any>(`${this.apiUrl}/history`, {
            headers: { Authorization: this.getToken() }
        });
    }

    //chat with ai and save message
    chat(prompt: string): Observable<any> {
        console.log(this.getToken());   //for debugging

        return this.http.get<any>(`${this.apiUrl}/chat`, {
            params: { prompt },
            headers: { Authorization: this.getToken() }, responseType: 'text' as 'json'
        });
    }
}
