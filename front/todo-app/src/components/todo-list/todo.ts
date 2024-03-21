import dayjs from "dayjs"

export interface Todo {
  id: number,
  title: string,
  description?: string,
  date: dayjs.Dayjs,
  attachments?: File[]
}

export const rows: Todo[] = [
  {
    id: 1,
    date: dayjs(),
    title: 'Buy eggs',
    description: 'Need eggs for an omelette'
  },

  {
    id: 2,
    date: dayjs(),
    title: 'Pay taxes',
    description: 'I dont want to go to jail'
  },

  
  {
    id: 3,
    date: dayjs(),
    title: 'Play games',
    description: 'I want to finish the game from the other day'
  }

]

// MODEL.
// export interface Data {
//     name: string;
//     code: string;
//     population: number;
//     size: number;
//     density: number;
//   }
  
//   // BUILD MODEL.
//   function createData(
//     name: string,
//     code: string,
//     population: number,
//     size: number,
//   ): Data {
//     const density = population / size;
//     return { name, code, population, size, density };
//   }
  
//   // BUILD MODEL.
// export const rows = [
//     createData('India', 'IN', 1324171354, 3287263),
//     createData('China', 'CN', 1403500365, 9596961),
//     createData('Italy', 'IT', 60483973, 301340),
//     createData('United States', 'US', 327167434, 9833520),
//     createData('Canada', 'CA', 37602103, 9984670),
//     createData('Australia', 'AU', 25475400, 7692024),
//     createData('Germany', 'DE', 83019200, 357578),
//     createData('Ireland', 'IE', 4857000, 70273),
//     createData('Mexico', 'MX', 126577691, 1972550),
//     createData('Japan', 'JP', 126317000, 377973),
//     createData('France', 'FR', 67022000, 640679),
//     createData('United Kingdom', 'GB', 67545757, 242495),
//     createData('Russia', 'RU', 146793744, 17098246),
//     createData('Nigeria', 'NG', 200962417, 923768),
//     createData('Brazil', 'BR', 210147125, 8515767),
//   ];
  