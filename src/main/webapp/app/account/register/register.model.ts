export class Registration {
  constructor(
    public login: string,
    public nationalId: string,
    public firstName: string,
    public lastName: string,
    public email: string,
    public password: string,
    public langKey: string,
  ) {}
}
