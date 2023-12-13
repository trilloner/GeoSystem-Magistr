import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ListImpl} from '../../model/list';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

  url = 'http://localhost:8080/device';

  fileServiceUrl = 'http://localhost:8090';

  headers = new HttpHeaders({Authorization: 'Basic ' + localStorage.getItem('token')});

  constructor(private httpClient: HttpClient) {
  }

  getAll(): Observable<ListImpl> {
    return this.httpClient.get<ListImpl>(this.url + '/all', {headers: this.headers});
  }

  uploadFile(fileToUpload: File): Observable<any> {

    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', fileToUpload, fileToUpload.name);

    return this.httpClient.post('http://localhost:8090/upload', uploadImageData);
  }

}
