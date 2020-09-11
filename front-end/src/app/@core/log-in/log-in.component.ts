import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  loginForm: FormGroup;
  returnUrl: string;
  submitted = false;
  loginFailed = false;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private authService: AuthService) {
    if (authService.isAuthenticated()) {
      router.navigateByUrl("/clients");
    }
  }

  get f() { return this.loginForm.controls; }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
    this.returnUrl = this.route.snapshot.queryParams.returnUrl;
  }

  onSubmit() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }

    this.authService.login(
      this.f.username.value,
      this.f.password.value)
      .pipe(first())
      .subscribe(
        () => {
          const returnUrl = this.returnUrl ? this.returnUrl : '/clients';
          this.router.navigate([returnUrl]);
        }, (() => {
          this.loginFailed = true;
        }));
  }
}
