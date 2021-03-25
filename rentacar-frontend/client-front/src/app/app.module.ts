import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './home-page/home-page.component';
import { AdvancedSearchComponent } from './advanced-search/advanced-search.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptor/TokenInterceptor';
import { LoginService } from './service/login.service';
import { NewAdComponent } from './new-ad/new-ad.component';
import { AdListComponent } from './ad-list/ad-list.component';
import { CodebookComponent } from './codebook/codebook.component';
import { CreateReportComponent } from './create-report/create-report.component';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { ModalDetailsComponent } from './modal-details/modal-details.component';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { UsersListComponent } from './users-list/users-list.component';
import { CartListComponent } from './cart-list/cart-list.component';
import { RequestListComponent } from './request-list/request-list.component';
import { MessagesComponent } from './messages/messages.component';
import { ModalMessagesComponent } from './modal-messages/modal-messages.component';
import { CommentsListComponent } from './comments-list/comments-list.component';
import { SingleSignOnComponent } from './single-sign-on/single-sign-on.component';
import { AddAgentComponent } from './add-agent/add-agent.component';
import { ModalMapComponent } from './modal-map/modal-map.component';
import { ConfirmAccountComponent } from './confirm-account/confirm-account.component';
import { PriceListViewComponent } from './price-list-view/price-list-view.component';
import { PriceList } from './model/PriceList';
import { PriceListComponent } from './price-list/price-list.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    AdvancedSearchComponent,
    LoginComponent,
    RegistrationComponent,
    NewAdComponent,
    AdListComponent,
    CodebookComponent,
    CreateReportComponent,
    ModalDetailsComponent,
    UsersListComponent,
    CartListComponent,
    RequestListComponent,
    MessagesComponent,
    ModalMessagesComponent,
    CommentsListComponent,
    SingleSignOnComponent,
    AddAgentComponent,
    ModalMapComponent,
    ConfirmAccountComponent,
    PriceListViewComponent,
    PriceListComponent
  ],
  entryComponents: [ModalDetailsComponent, ModalMessagesComponent, ModalMapComponent,PriceListViewComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AngularFontAwesomeModule,
    NgbModalModule,
    ReactiveFormsModule
  ],
  providers: [
    {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  },
    LoginService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
