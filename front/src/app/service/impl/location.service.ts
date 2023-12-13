import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Location} from '../../model/location';
import {ListLocations} from '../../model/listLocations';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  url = 'http://localhost:8080/location';
  headers = new HttpHeaders({Authorization: 'Basic ' + localStorage.getItem('token')});

  constructor(private httpClient: HttpClient) {
  }

  getAll(): Observable<ListLocations> {
    return this.httpClient.get<ListLocations>(this.url + '/all', {headers: this.headers});
  }

  getLocationByFieldId(fieldId: number): Observable<Location> {
    return this.httpClient.get<Location>(this.url + '/field/' + fieldId, {headers: this.headers});
  }

  saveLocation(location: Location): Observable<any> {
    console.log(location);
    return this.httpClient.post<Location>(this.url + '/add', location, {headers: this.headers});
  }
}
