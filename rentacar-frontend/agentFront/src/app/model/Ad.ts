import { Car } from '../model/Car';
import { PriceList } from './PriceList';

export class Ad {
    id : number;
    car: Car = new Car();
    city: String = "";
    collisionDamageWaiver : boolean;
    userID: number;
    startDate: string = "";
    endDate: string = "";
    comments: Comment[] = [];
    rating: number;
    priceListID: number;
    discount: number;
    priceList: PriceList;
}