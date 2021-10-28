class Human {
  public name: string
  public age: number
  public gender: string
  constructor(name:string, age:number, gender:string){
    this.name = name
    this.age = age
    this.gender = gender
  }
}

const lynn = new Human("Lynn", 17, "female")

const person = {
  name: 'tom',
  age: 22,
  gender: 'male'
}

const name = 'jack',
  age = 18,
  gender = 'male'

const sayHi = (person:Human): string => {
  return `Hello ${person.name}, you are ${person.age}, you are a ${person.gender}!`
}

console.log(sayHi(person))
console.log(sayHi(lynn))

export {}
