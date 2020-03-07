export interface IUserModel {
  _id?: string
  email: string
  firstName: string
  lastName: string
  role: string
  status: number
  createdAt?: string
  updatedAt?: string
  colors?: {
    [className: string]: string,
  }
}
