export interface Workation {
 workationId:string; employee:string; origin:string; destination:string;
 start:string; end:string; workingDays:number; risk:string;
}
export type SortKey = keyof Workation;
