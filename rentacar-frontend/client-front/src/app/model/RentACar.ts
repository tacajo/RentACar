import { Ad } from './Ad';
import { User } from './User';

export class RentACar {
    id : number;
    startDate: string;
    endDate: string;
    adId : number;
    adDTO : Ad = new Ad();
    userID : number;
    ad;
    adUser: User = new User();
}