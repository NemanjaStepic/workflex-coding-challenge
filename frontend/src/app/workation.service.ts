import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Workation } from './workation.model';
@Injectable({providedIn:'root'}) export class WorkationService {
 private http=inject(HttpClient);
 list():Observable<Workation[]>{return this.http.get<Workation[]>('http://localhost:8080/workflex/workation');}
}
