import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

export interface App {
  name: string;
  version: string;
}

@Injectable({
  providedIn: 'root',
})
export class AppService {

  app!: App;
  appUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {
  }

  getApp(): Promise<App | undefined> {
    return this.http.get<App>(this.appUrl).toPromise();
  }

}
