console.time("sum");
let sum = 0;
for(let i=0;i<1000;i++){
    sum = sum + i;
}
console.timeEnd("sum");
