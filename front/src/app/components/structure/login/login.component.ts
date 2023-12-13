import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../../service/impl/authentication.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';

  constructor(private authService: AuthenticationService,
              private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router) {
    if (this.authService.currentUserValue) {
      this.router.navigate(['cabinet']);
    }
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  get f(): any {
    return this.loginForm.controls;
  }

  login(): void {
    console.log(this.f.username.value, this.f.password.value);
    this.authService.login(this.f.username.value, this.f.password.value).subscribe(data => {
        this.router.navigate(['home']);
      },
      error => {
        console.log(error);
        this.error = error;
        this.loading = false;
      });
  }
}
