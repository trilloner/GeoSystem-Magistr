import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Field} from '../../model/field';
import {FieldDTO} from '../../model/fieldDTO';

@Injectable({
  providedIn: 'root'
})
export class FieldService {
  url = 'http://localhost:8080/field';
  headers = new HttpHeaders({Authorization: 'Basic ' + localStorage.getItem('token')});

  constructor(private httpClient: HttpClient) {
  }

  getAll(): Observable<Field> {
    console.log(localStorage.getItem('token'));
    return this.httpClient.get<Field>(this.url + '/geojson/all', {headers: this.headers});
  }

  saveField(field: FieldDTO): Observable<number> {
    return this.httpClient.post<number>(this.url + '/add', field, {headers: this.headers});
  }

}
