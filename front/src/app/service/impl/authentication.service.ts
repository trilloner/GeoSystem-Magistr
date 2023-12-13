import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from '../../model/user';
import {map} from 'rxjs/operators';
import {ActivatedRoute, Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private url = 'http://localhost:8080/';
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient,
              private route: ActivatedRoute,
              private router: Router) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): any {
    return this.currentUserSubject.value;
  }

  login(username: string, password: string): any {

    const base64Credential = btoa(username + ':' + password);
    const headers = new HttpHeaders({Authorization: 'Basic ' + base64Credential});
    return this.http.get<any>(`http://localhost:8080/user/login`, {
      headers,
      responseType: 'text' as 'json'
    })
      .pipe(map(user => {
        // store user details and basic auth credentials in local storage to keep user logged in between page refreshes
        console.log(user);
        localStorage.setItem('currentUser', JSON.stringify(user));
        localStorage.setItem('token', base64Credential);
        this.currentUserSubject.next(user);
        return user;
      }));
  }

  logout(): any {

    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
    return this.router.navigate(['login']);
  }

  registration(user: User): any {
    return this.http.post('http://localhost:8080/user/register', user);
  }
}
